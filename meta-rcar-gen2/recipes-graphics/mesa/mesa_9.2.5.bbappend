def map_libs(d):
    if base_contains('MACHINE_FEATURES', 'sgx', "1", "0", d) == "0":
        if base_contains('MACHINE_FEATURES', 'rgx', "1", "0", d) == "0":
            return "dummy"

    if base_contains('DISTRO_FEATURES', 'wayland', '1', '0', d) == "1":
        return "wayland"
    if base_contains('DISTRO_FEATURES', 'x11', '1', '0', d) == "1":
        return "x11"

    return "dummy"

MESATARGET := "${@map_libs(d)}"
include mesa-${MESATARGET}.inc
