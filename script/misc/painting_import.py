#!/usr/bin/env python

import os
import httplib, urllib, mimetypes

def post_multipart(host, selector, fields, files):
    content_type, body = encode_multipart_formdata(fields, files)
    h = httplib.HTTPConnection(host)
    headers = {
        'Content-Type': content_type
        }
    h.request('POST', selector, body, headers)
    res = h.getresponse()
    return res

def encode_multipart_formdata(fields, files):
    BOUNDARY = '----------ThIs_Is_tHe_bouNdaRY_$'
    CRLF = '\r\n'
    L = []
    for (key, value) in fields:
        L.append('--' + BOUNDARY)
        L.append('Content-Disposition: form-data; name="%s"' % key)
        L.append('')
        L.append(value)
    for (key, filename, value) in files:
        L.append('--' + BOUNDARY)
        L.append('Content-Disposition: form-data; name="%s"; filename="%s"' % (key, filename))
        L.append('Content-Type: %s' % get_content_type(filename))
        L.append('')
        L.append(value)
    L.append('--' + BOUNDARY + '--')
    L.append('')
    body = CRLF.join(L)
    content_type = 'multipart/form-data; boundary=%s' % BOUNDARY
    return content_type, body

def get_content_type(filename):
    return mimetypes.guess_type(filename)[0] or 'application/octet-stream'

def cap(word_with_underscores):
    parts = word_with_underscores.split("_")
    return " ".join(map(str.capitalize, parts))
    
def import_paintings(paintings_path):
    conn = httplib.HTTPConnection("localhost", 7070)
    try:
        for file in os.listdir(paintings_path):
            tokens = file.split(".") 
            if len(tokens) == 2 and tokens[1] == "jpg":
                artist, title = map(cap, tokens[0].split("|"))
                fields = [("painting-artist", artist), ("painting-title", title), ("painting-strategy", "1"), ("painting-tags", "")]
                files = [("uploaded-file", file, open(os.path.join(paintings_path,file), 'r').read())]
                post_multipart("localhost:7070", "/panel/upload", fields, files)
    except:
        pass
    
if __name__ == "__main__":
    paintings_path = os.path.join("/Users/alexis","Desktop","Dev","TSP","paintings")
    import_paintings(paintings_path)