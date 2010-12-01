#!/usr/bin/env python

import unittest
from org.iproj.file.uploader import Uploader, WrongExtensionError, WrongMimetypeError, SizeLimitExceededError, NullRequestError

class DummyFileRequest:
    
    def __init__(self, ext = "png", ct = "image/png"):
        self.files = {"test": [{"filename" : "testimg." + ext, "body": "test body", "content_type" : ct },
                               {"filename" : "testimg1." + ext, "body": "test body22", "content_type" : ct }]}
        
class UploaderTestModule(unittest.TestCase):
    
    def test_all_fine(self):
        dummy_request = DummyFileRequest()
        try:
            uploader = Uploader(dummy_request).size(100).extensions(["png"]).mimetypes(["image/png"]).check()
        except Exception, (instance):
            self.fail(instance.message)
            
    def test_wrong_extension(self):
        dummy_request = DummyFileRequest(ext = "doc")
        try:
            uploader = Uploader(dummy_request).extensions(["png"]).check()
        except WrongExtensionError, (instance):
            pass
        else:
            self.fail("WrongExtensionError was not thrown.")
    
    def test_wrong_mimetype(self):
        dummy_request = DummyFileRequest(ct = "image/jpeg")
        try:
            uploader = Uploader(dummy_request).mimetypes(["image/png"]).check()
        except WrongMimetypeError, (instance):
            pass
        else:
            self.fail("WrongMimetypeError was not thrown.")
    
    def test_forged_content_type(self):
        dummy_request = DummyFileRequest(ct = "image/png", ext = "jpg")
        try:
            uploader = Uploader(dummy_request).mimetypes(["image/png"]).extensions(["png", "jpg"]).check()
        except WrongMimetypeError, (instance):
            pass
        else:
            self.fail("WrongMimetypeError was not thrown.")
    
    def test_exceeds_size_limit(self):
        dummy_request = DummyFileRequest()
        try:
            uploader = Uploader(dummy_request).size(5).check()
        except SizeLimitExceededError, (instance):
            pass
        else:
            self.fail("SizeLimitExceededError was not thrown.")
    
    def test_random_errors(self):
        dummy_request = DummyFileRequest(ext = "doc", ct = "image/jpg")
        try:
            uploader = Uploader(dummy_request).size(5).mimetypes("image/png").extensions(["png"]).check()
        except SizeLimitExceededError, (instance):
            pass
        except WrongMimetypeError, (instance):
            pass
        except WrongExtensionError, (instance):
            pass
        else:
            self.fail("No exception was thrown.")
    
    def test_null_request(self):
        dummy_request = None
        try:
            uploader = Uploader(dummy_request).extensions(["png"]).check()
        except NullRequestError, (instance):
            pass
        else:
            self.fail("NullRequestError was not thrown.")
    
if __name__ == '__main__':
    unittest.main()