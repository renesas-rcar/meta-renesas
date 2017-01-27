FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

# Add W/A patch for linaro gcc.
# This patch disabled neon. (undefined #WEBP_USE_NEON)
SRC_URI_append = " file://disabled_arm_neon.diff"
