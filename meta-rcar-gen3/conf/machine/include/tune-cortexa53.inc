TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa53", "cortexa53:", "" ,d)}"
