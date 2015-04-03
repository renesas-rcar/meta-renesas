require ../../include/gles-control.inc
require ../../include/multimedia-control.inc

DESCRIPTION = "Package Group for multimedia of R-Car Gen2"
LICENSE = "CLOSED"

inherit packagegroup

PACKAGES = "\
    packagegroup-rcar-gen2-multimedia \
    packagegroup-rcar-gen2-multimedia-tp \
    packagegroup-rcar-gen2-dtv \
"
PACKAGES_append_lcb = " packagegroup-lcb-oss-codecs"

MULTIMEDIA_PACKAGES ="\
    mmngr-kernel-module mmngr-user-module \
    mmngrbuf-kernel-module mmngrbuf-user-module \
    mmngrbuf-user-module-dev \
    fdpm-kernel-module fdpm-user-module \
    vspm-kernel-module vspm-user-module \
    s3ctl-kernel-module s3ctl-user-module \
    uvcs-kernel-module omx-user-module \
    libmemcpy \
"

MULTIMEDIA_PACKAGES_append = " \
    ${@ "vsp2-kernel-module" if "${USE_GLES_WAYLAND}" == "1" else "" } \
"

RDEPENDS_packagegroup-rcar-gen2-multimedia = "\
    ${@ "${MULTIMEDIA_PACKAGES}" if "${USE_MULTIMEDIA}" == "1" else "" } \
    media-ctl \
	gstreamer1.0-meta-base \
	gstreamer1.0-meta-audio \
	gstreamer1.0-meta-video \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-audioparsers \
	gstreamer1.0-plugins-good-id3demux \
    gstreamer1.0-plugins-bad-faad \
    gstreamer1.0-plugins-bad-mpegtsdemux \
    gstreamer1.0-plugins-bad-debugutilsbad \
    ${@base_contains("LICENSE_FLAGS_WHITELIST", "commercial", "gstreamer1.0-omx gstreamer1.0-plugins-ugly-asf", "", d )} \
    ${@base_conditional("USE_GLES_WAYLAND", "1", "gstreamer1.0-plugins-base-vspfilter", "", d )} \
"

MULTIMEDIA_TEST_PACKAGES = "\
    ${MULTIMEDIA_PACKAGES} \
    mmngr-tp-user-module \
    mmngrbuf-tp-user-module \
    fdpm-tp-user-module \
    vspm-tp-user-module \
    s3ctl-tp-user-module \
"

RDEPENDS_packagegroup-rcar-gen2-multimedia-tp = "\
    ${@ '${MULTIMEDIA_TEST_PACKAGES}' if '${USE_MULTIMEDIA}' == '1' and '${USE_MULTIMEDIA_TEST}' == '1' else '' } \
"

DTV_PACKAGES = "\
    ${MULTIMEDIA_PACKAGES} \
    scu-kernel-module ssp-kernel-module \
    dtv \
"

RDEPENDS_packagegroup-rcar-gen2-dtv = "\
    ${@ '${DTV_PACKAGES}' if '${USE_DTV}' == '1' else '' } \
"

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
