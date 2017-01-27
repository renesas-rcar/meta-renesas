require include/gles-control.inc
require include/multimedia-control.inc

PACKAGECONFIG_remove_virtclass-multilib-lib32 = "launch"
DEPENDS_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

RDEPENDS_${PN}_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)} \
    ${@base_conditional('USE_MULTIMEDIA', '1', ' media-ctl', '', d)} \
"
RDEPENDS_${PN}-examples_append = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

EXTRA_OECONF_append = " \
    ${@base_conditional('USE_GLES', '1', '', \
        ' WESTON_NATIVE_BACKEND="fbdev-backend.so"', d)}"

PACKAGES_prepend = "${PN}-bin "

FILES_${PN}-bin = " \
    ${bindir}/weston \
    ${bindir}/weston-terminal \
    ${bindir}/weston-info \
    ${bindir}/weston-launch \
    ${bindir}/wcap-decode \
"

FILES_${PN} = " \
    ${libdir}/weston \
    ${datadir} \
    ${libexecdir} \
"

USERADD_PACKAGES = "${PN}-bin"
GROUPADD_PARAM_${PN}-bin = "--system weston-launch"
