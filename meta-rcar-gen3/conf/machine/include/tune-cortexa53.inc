DEFAULTTUNE ?= "cortexa53"
require conf/machine/include/arm/arch-armv8.inc

TUNEVALID[cortexa53] = "Enable Cortex-A53 specific processor optimizations"
TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "aarch64", " -march=armv8-a", "" ,d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa53", "cortexa53:", "" ,d)}"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "cortexa53", " -mtune=cortex-a53", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa53"
ARMPKGARCH_tune-cortexa53 = "cortexa53"
TUNE_FEATURES_tune-cortexa53 = "${TUNE_FEATURES_tune-aarch64} cortexa53"
PACKAGE_EXTRA_ARCHS_tune-cortexa53 = "${PACKAGE_EXTRA_ARCHS_tune-aarch64} cortexa53"
BASE_LIB_tune-cortexa53 = "lib64"
