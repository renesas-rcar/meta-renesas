require include/gles-control.inc
require include/multimedia-control.inc

PACKAGECONFIG:remove:virtclass-multilib-lib32 = "launch"

DEPENDS:append:rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)}"

RDEPENDS:${PN}:append:rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)} \
"
RDEPENDS:${PN}-examples:append:rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', ' libgbm', '', d)}"

EXTRA_OEMESON:append:rcar-gen3 = " \
    ${@oe.utils.conditional('USE_GLES', '1', '', \
        ' -Dbackend-default="fbdev"', d)}"

EXTRA_OEMESON:append = " -Dsimple-clients=egl,shm,damage,im,touch"
