DEPENDS += "gstreamer libxml2"

EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"
EXTRA_OECONF += "--with-plugins=h264parse,asfmux,videoparsers"
PACKAGECONFIG += "faad"

TARGET_CFLAGS += "-D_GNU_SOURCE"
PRINC := "${@int(PRINC) + 1}"

