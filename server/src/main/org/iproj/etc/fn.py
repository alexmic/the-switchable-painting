#!/usr/bin/env python

import os

def find_abs_path(where, filepath):
    abs_path = os.path.abspath(filepath) 
    tokens = abs_path.split("/")
    if where in tokens:
        return os.path.join("/", *tokens[0: tokens.index(where) + 1])
    else:
        return abs_path
            