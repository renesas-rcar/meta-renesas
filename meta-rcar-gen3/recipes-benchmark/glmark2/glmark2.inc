SRC_URI:append = " file://Change-gbm-header-path.patch"

PACKAGECONFIG[drm-gl] = ",,virtual/libgl libdrm libgbm"
PACKAGECONFIG[drm-gles2] = ",,virtual/libgles2 libdrm libgbm"

