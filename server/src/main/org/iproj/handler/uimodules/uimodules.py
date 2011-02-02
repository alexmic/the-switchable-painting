#!/usr/bin/env python
#!/usr/bin/env python
import tornado.web

class SimImage(tornado.web.UIModule):
    def render(self, img):
        return self.render_string("uimodules/sim-image.html", img=img)
