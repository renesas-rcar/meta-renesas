require include/gles-control.inc
DEPENDS:append:rcar-gen3 = " \
    ${@'gles-user-module virtual/libgbm' if '${USE_GLES_WAYLAND}' == '1'  else ''} \
"
