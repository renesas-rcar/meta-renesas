DEPENDS += "gstreamer faad2 libxml2"

EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-experimental', '--enable-experimental')}"
EXTRA_OECONF += "--with-plugins=h264parse,asfmux,videoparsers"
TARGET_CFLAGS += "-D_GNU_SOURCE"
PRINC := "${@int(PRINC) + 1}"

