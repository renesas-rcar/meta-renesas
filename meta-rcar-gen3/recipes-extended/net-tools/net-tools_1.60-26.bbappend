FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# This patch is to fix compile error when build net-tools
# with Linux kernel 4.8 and 4.9.
SRC_URI_append = " \
    file://net-tools-fix-building-with-linux-4.8.patch \
"
