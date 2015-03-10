PACKAGES_append_silk = " packagegroup-lcb-oss-codecs"
PACKAGES_append_porter = " packagegroup-lcb-oss-codecs"

RDEPENDS_packagegroup-lcb-oss-codecs = "\
	libmad \
	lame \
	faac \
	faad2 \
	libvorbis \
	libogg \
	gstreamer1.0-plugins-ugly-mad \
	gstreamer1.0-plugins-ugly-lame \
	gstreamer1.0-plugins-bad-faac \
	gstreamer1.0-plugins-bad-faad \
	gstreamer1.0-plugins-base-ogg \
	gstreamer1.0-plugins-base-vorbis \
"
