DEFAULTTUNE ?= "cortexa57"
require conf/machine/include/arm/arch-armv8.inc

TUNEVALID[cortexa57] = "Enable Cortex-A57 specific processor optimizations"
TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "aarch64", " -march=armv8-a", "" ,d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa57", "cortexa57:", "" ,d)}"

TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "cortexa57", " -mtune=cortex-a57", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa57"
ARMPKGARCH_tune-cortexa57 = "cortexa57"
TUNE_FEATURES_tune-cortexa57 = "${TUNE_FEATURES_tune-aarch64} cortexa57"
PACKAGE_EXTRA_ARCHS_tune-cortexa57 = "${PACKAGE_EXTRA_ARCHS_tune-aarch64} cortexa57"
BASE_LIB_tune-cortexa57 = "lib64"
