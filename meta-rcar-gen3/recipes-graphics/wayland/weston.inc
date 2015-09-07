require ../../include/gles-control.inc

PACKAGECONFIG_rcar-gen3 := "${@'${PACKAGECONFIG}'.replace('x11', '')}"
DEPENDS_append_rcar-gen3 = " \
        ${@base_conditional('USE_GLES', '1', 'libegl', '', d)}"

RDEPENDS_${PN}_append_rcar-gen3 = " \
        ${@base_conditional('USE_GLES', '1', 'libegl', '', d)}"
RDEPENDS_${PN}-examples_append_rcar-gen3 = " \
        ${@base_conditional('USE_GLES', '1', 'libegl', '', d)}"
