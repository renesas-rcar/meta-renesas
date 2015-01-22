require ../../include/gles-control.inc

SRC_URI_rcar-gen2 = "git://github.com/renesas-devel/gst-omx.git;protocol=git;branch=RCAR-GEN2/1.0.0"
SRCREV_rcar-gen2 = "05563465faad99243ee2dd30547e3075eb8cf5e3"

LIC_FILES_CHKSUM_remove_rcar-gen2 = " file://omx/gstomx.h;beginline=1;endline=21;md5=5c8e1fca32704488e76d2ba9ddfa935f" 
LIC_FILES_CHKSUM_append_rcar-gen2 = " file://omx/gstomx.h;beginline=1;endline=22;md5=17e5f2943dace9e5cde4a8587a31e8f9"
S = "${WORKDIR}/git"

do_configure() {
	./autogen.sh --noconfigure
	oe_runconf
}

DEPENDS_append_rcar-gen2 = " omx-user-module mmngrbuf-user-module"
EXTRA_OECONF_append_rcar-gen2 = " --with-omx-target=rcar --enable-experimental \
    '${@'--disable-dmabuf' if '${USE_GLES}' == '0' and '${USE_WAYLAND}' == '1' else ''}'"

# Overwrite do_install[postfuncs] += " set_omx_core_name "
# because it will force the plugin to use bellagio instead of our config
revert_omx_core_name() {
    sed -i -e "s;^core-name=.*;core-name=/usr/local/lib/libomxr_core.so;" "${D}/etc/xdg/gstomx.conf"
}
REVERT_OMX_CORE_NAME = ""
REVERT_OMX_CORE_NAME_rcar-gen2 = "revert_omx_core_name"
do_install[postfuncs] += "${REVERT_OMX_CORE_NAME}"
