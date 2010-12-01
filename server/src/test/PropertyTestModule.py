#!/usr/bin/env python

from org.iproj.etc.property import Property, PropertyError
import unittest

class PropertyTestCase(unittest.TestCase):
    
    def test_allfine(self):
        try:
            properties = Property(["p1=10", " p2=10", "p3 =10", "p4= 10", "p5=10 "])
            self.assertEquals(properties.p1, "10", "p1 was not parsed correctly.")
            self.assertEquals(properties.p2, "10", "p2 was not parsed correctly.")
            self.assertEquals(properties.p3, "10", "p3 was not parsed correctly.")
            self.assertEquals(properties.p4, "10", "p4 was not parsed correctly.")
            self.assertEquals(properties.p5, "10", "p5 was not parsed correctly.")        
        except PropertyError, (instance):
            self.fail(instance.message)
            
    def test_wrong_format(self):
        try:
            Property(["p1===10", " p2&=10", "p3 \=10"])
        except PropertyError:
            pass
        else:
            self.fail("Exception not thrown on wrong format.")
    
    def test_null_properties(self):
        try:
            properties = Property(None)
            self.assertEquals(len(properties.__dict__), 0, "Null properties had length > 0. WTF?")
        except PropertyError, (instance):
            self.fail("Exception thrown with message " + instance.message)

    def test_empty_value(self):
        try:
            Property(["p1="])
        except PropertyError:
            pass
        else:
            self.fail("Exception not thrown on empty value.")
 
    def test_empty_key(self):
        try:
            Property(["=10"])
        except PropertyError:
            pass
        else:
            self.fail("Exception not thrown on empty key.")
    
    def test_empty_properties(self):
        try:
            properties = Property([])
            self.assertEquals(len(properties.__dict__), 0, "Length of data for empty properties > 0. WTF?")
        except PropertyError, (instance):
            self.fail(instance.message)

if __name__ == '__main__':
    unittest.main()

