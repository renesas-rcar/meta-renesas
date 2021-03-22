SUMMARY = "GStreamer 1.0 package groups"
LICENSE = "MIT"

require include/omx-control.inc

COMPATIBLE_MACHINE = "(salvator-x|ulcb|ebisu|draak)"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good"
DEPENDS += "gstreamer1.0-plugins-bad"
DEPENDS += "gstreamer1.0-plugins-ugly"
DEPENDS += "${@oe.utils.conditional("USE_OMX_COMMON", "1", "gstreamer1.0-omx", "", d)}"

LIC_FILES_CHKSUM = " \
    file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
"

PR = "r0"

inherit packagegroup

PACKAGES = " \
    packagegroup-gstreamer1.0-plugins \
    packagegroup-gstreamer1.0-plugins-base \
    packagegroup-gstreamer1.0-plugins-audio \
    packagegroup-gstreamer1.0-plugins-video \
    ${@oe.utils.conditional("USE_OMX_COMMON", "1", "packagegroup-gstreamer1.0-omx", "", d)} \
    packagegroup-gstreamer1.0-plugins-debug \
"

RDEPENDS_packagegroup-gstreamer1.0-plugins = " \
    packagegroup-gstreamer1.0-plugins-base \
    packagegroup-gstreamer1.0-plugins-audio \
    packagegroup-gstreamer1.0-plugins-video \
    ${@oe.utils.conditional("USE_OMX_COMMON", "1", "packagegroup-gstreamer1.0-omx", "", d)} \
    packagegroup-gstreamer1.0-plugins-debug \
"

RDEPENDS_packagegroup-gstreamer1.0-plugins-base = " \
    gstreamer1.0-meta-base \
    gstreamer1.0-plugins-base-typefindfunctions \
    gstreamer1.0-plugins-good-id3demux \
    gstreamer1.0-plugins-good-autodetect \
    ${@oe.utils.conditional("USE_OMX_COMMON", "1", "gstreamer1.0-plugin-vspfilter", "", d)} \
"

RDEPENDS_packagegroup-gstreamer1.0-plugins-audio = " \
    gstreamer1.0-meta-audio \
    gstreamer1.0-plugins-good-audioparsers \
    gstreamer1.0-plugins-base-audiotestsrc \
    gstreamer1.0-plugins-base-audioconvert \
    gstreamer1.0-plugins-base-audioresample \
    gstreamer1.0-plugins-base-alsa \
    gstreamer1.0-plugins-base-ogg \
    gstreamer1.0-plugins-base-vorbis \
"

RDEPENDS_packagegroup-gstreamer1.0-plugins-video = " \
    gstreamer1.0-meta-video \
    gstreamer1.0-plugins-base-videotestsrc \
    gstreamer1.0-plugins-base-videoconvert \
    gstreamer1.0-plugins-base-playback \
    gstreamer1.0-plugins-base-videoscale \
    gstreamer1.0-plugins-base-videorate \
    gstreamer1.0-plugins-good-matroska \
    gstreamer1.0-plugins-good-isomp4 \
    gstreamer1.0-plugins-good-avi \
    gstreamer1.0-plugins-good-videofilter \
    gstreamer1.0-plugins-good-videomixer \
    gstreamer1.0-plugins-good-videocrop \
    gstreamer1.0-plugins-good-video4linux2 \
    gstreamer1.0-plugins-good-jpeg \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', \
        'gstreamer1.0-plugins-bad-waylandsink', '', d)} \
    gstreamer1.0-plugins-bad-videoparsersbad \
    gstreamer1.0-plugins-bad-mpegtsdemux \
    gstreamer1.0-plugins-bad-jpegformat \
    gstreamer1.0-plugins-ugly-asf \
    gstreamer1.0-libav \
"

RDEPENDS_packagegroup-gstreamer1.0-omx = " \
    ${@oe.utils.conditional("USE_OMX_COMMON", "1", "gstreamer1.0-omx", "", d)} \
"

RDEPENDS_packagegroup-gstreamer1.0-plugins-debug = " \
    gstreamer1.0-meta-debug \
"
