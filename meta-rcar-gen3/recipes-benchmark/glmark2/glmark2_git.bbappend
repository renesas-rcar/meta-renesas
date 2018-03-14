FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRC_URI_append = " \
    file://0001-gl-state-egl-Avoid-build-failure-due-to-miss-macro-d.patch \
"

PACKAGECONFIG[drm-gl] = ",,virtual/libgl libdrm libgbm"
PACKAGECONFIG[drm-gles2] = ",,virtual/libgles2 libdrm libgbm"
