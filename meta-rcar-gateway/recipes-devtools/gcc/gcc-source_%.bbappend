FILESEXTRAPATHS_prepend := "${THISDIR}/gcc:"

SRC_URI_append = " \
                file://0037-libatomic-Do-not-enforce-march-on-aarch64.patch \
"
