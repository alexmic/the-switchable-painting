#!/usr/bin/env python

import PropertyTestModule, UploaderTestModule
import unittest
import sys

def run():
    MODULES_TO_TEST = [PropertyTestModule, UploaderTestModule]
    runner = unittest.TextTestRunner()
    loader = unittest.TestLoader()
    HAS_ERRORS = False
    for module in MODULES_TO_TEST:
        result = runner.run(loader.loadTestsFromModule(module))
        HAS_ERRORS |= len(result.errors) > 0 or len(result.failures) > 0
    if HAS_ERRORS:
        sys.exit(1)
    
if __name__ == "__main__":
    run()