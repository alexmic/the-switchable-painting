#!/usr/bin/env python

import PropertyTestModule, UploaderTestModule
import unittest

def run():
    MODULES_TO_TEST = [PropertyTestModule, UploaderTestModule]
    runner = unittest.TextTestRunner()
    loader = unittest.TestLoader()
    for module in MODULES_TO_TEST:
        runner.run(loader.loadTestsFromModule(module))
    
if __name__ == "__main__":
    run()