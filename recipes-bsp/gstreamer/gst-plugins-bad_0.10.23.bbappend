DEPENDS += "gstreamer directfb faad2 libxml2"

EXTRA_OECONF := "${@'${EXTRA_OECONF}'.replace('--disable-directfb', '--enable-directfb')}"
EXTRA_OECONF += "--with-plugins=h264parse"

PRINC := "${@int(PRINC) + 1}"
