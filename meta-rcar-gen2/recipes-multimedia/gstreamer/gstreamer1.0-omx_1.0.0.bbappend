SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-omx.git;protocol=git;branch=RCAR-GEN2/1.0.0"
SRCREV_rcar-gen2 = "f109a467062ea0b351f2ef7923cb9096032c6d56"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c \
                    file://omx/gstomx.h;beginline=1;endline=22;md5=0d05f437fd2cd9a30dba5ff8e826e8f7"
S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

DEPENDS_append_rcar-gen2 = " omx-user-module mmngrbuf-user-module"
EXTRA_OECONF_append_rcar-gen2 = " --with-omx-target=rcar --enable-experimental"

# Overwrite do_install[postfuncs] += " set_omx_core_name "
# because it will force the plugin to use bellagio instead of our config
revert_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=/usr/local/lib/libomxr_core.so;" "${D}/etc/xdg/gstomx.conf"
}
REVERT_OMX_CORE_NAME = ""
REVERT_OMX_CORE_NAME_rcar-gen2 = "revert_omx_core_name"
do_install[postfuncs] += "${REVERT_OMX_CORE_NAME}"
