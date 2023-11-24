FILESEXTRAPATHS_prepend_rcar-gateway := "${THISDIR}/gcc:"

SRC_URI_append_rcar-gateway = " \
                file://0037-libatomic-Do-not-enforce-march-on-aarch64.patch \
"
