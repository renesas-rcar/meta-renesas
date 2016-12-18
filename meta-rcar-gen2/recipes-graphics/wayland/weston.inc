require include/gles-control.inc
inherit groupadd_useradd_prepend

PACKAGECONFIG_rcar-gen2 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"
PACKAGECONFIG_virtclass-multilib-lib32 := "${@'${PACKAGECONFIG}'.replace('launch', '')}"
DEPENDS_append_rcar-gen2 = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

RDEPENDS_${PN}_append_rcar-gen2 = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"
RDEPENDS_${PN}-examples_append_rcar-gen2 = " \
    ${@base_conditional('USE_GLES', '1', ' libgbm', '', d)}"

EXTRA_OECONF_append = " \
    ${@base_conditional('USE_GLES', '1', '', \
    ' WESTON_NATIVE_BACKEND="fbdev-backend.so"', d)}"

EXTRA_OECONF_append = " \
    ${@base_conditional('USE_MULTIMEDIA', '1', '', \
    ' WESTON_NATIVE_BACKEND="fbdev-backend.so"', d)}"

PACKAGES_prepend = "${PN}-bin "

FILES_${PN}-bin = "${bindir}/weston \
    ${bindir}/weston-terminal \
    ${bindir}/weston-info \
    ${bindir}/weston-launch \
    ${bindir}/wcap-decode \
"

FILES_${PN} = " \
    ${libdir}/weston \
    ${datadir} \
"

USERADD_PACKAGES = "${PN}-bin"
GROUPADD_PARAM_${PN}-bin = "--system weston-launch"
