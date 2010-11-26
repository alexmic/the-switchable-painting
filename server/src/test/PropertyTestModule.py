#!/usr/bin/env python

from org.iproj.etc.property import Property, PropertyException
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
        except PropertyException, (instance):
            self.fail(instance.message)
            
    def test_wrongformat(self):
        try:
            Property(["p1===10", " p2&=10", "p3 \=10"])
        except PropertyException:
            pass
        else:
            self.fail("Exception not thrown on wrong format.")
    
    def test_nullproperties(self):
        try:
            properties = Property(None)
            self.assertEquals(len(properties.__dict__), 0, "Null properties had length > 0. WTF?")
        except PropertyException, (instance):
            self.fail("Exception thrown with message " + instance.message)

    def test_emptyvalue(self):
        try:
            Property(["p1="])
        except PropertyException:
            pass
        else:
            self.fail("Exception not thrown on empty value.")
 
    def test_emptykey(self):
        try:
            Property(["=10"])
        except PropertyException:
            pass
        else:
            self.fail("Exception not thrown on empty key.")
    
    def test_emptyproperties(self):
        try:
            properties = Property([])
            self.assertEquals(len(properties.__dict__), 0, "Length of data for empty properties > 0. WTF?")
        except PropertyException, (instance):
            self.fail(instance.message)

if __name__ == '__main__':
    unittest.main()

