DEFAULTTUNE ?= "cortexa57-cortexa53"

TUNEVALID[cortexa57-cortexa53] = "Enable Cortex-A57.Cortex-A53 specific processor optimizations"
TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'cortexa57-cortexa53', ' -mcpu=cortex-a57.cortex-a53', '', d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa57-cortexa53", "cortexa57-cortexa53:", "" ,d)}"
TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

require conf/machine/include/arm/arch-armv8a.inc

# Little Endian base configs
AVAILTUNES += "cortexa57-cortexa53 cortexa57-cortexa53-crypto"
ARMPKGARCH_tune-cortexa57-cortexa53             = "cortexa57-cortexa53"
ARMPKGARCH_tune-cortexa57-cortexa53-crypto      = "cortexa57-cortexa53"
TUNE_FEATURES_tune-cortexa57-cortexa53          = "aarch64 cortexa57-cortexa53 crc"
TUNE_FEATURES_tune-cortexa57-cortexa53-crypto   = "aarch64 cortexa57-cortexa53 crc crypto"
PACKAGE_EXTRA_ARCHS_tune-cortexa57-cortexa53             = "${PACKAGE_EXTRA_ARCHS_tune-armv8a-crc} cortexa57-cortexa53"
PACKAGE_EXTRA_ARCHS_tune-cortexa57-cortexa53-crypto      = "${PACKAGE_EXTRA_ARCHS_tune-armv8a-crc-crypto} cortexa57-cortexa53 cortexa57-cortexa53-crypto"
BASE_LIB_tune-cortexa57-cortexa53               = "lib64"
BASE_LIB_tune-cortexa57-cortexa53-crypto        = "lib64"
