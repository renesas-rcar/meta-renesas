require include/gles-control.inc

def map_libs(d):
    if base_conditional('USE_GLES_WAYLAND', "1", "1", "0", d) == "1":
        return "wayland"

    return "dummy"

MESATARGET := "${@map_libs(d)}"
include mesa-${MESATARGET}.inc
