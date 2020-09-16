DEFAULTTUNE ?= "cortexa76"

TUNEVALID[cortexa76] = "Enable Cortex-A76 specific processor optimizations"

TUNE_CCARGS .= "${@bb.utils.contains('TUNE_FEATURES', 'cortexa76', ' -mcpu=cortex-a76', '', d)}"

MACHINEOVERRIDES =. "${@bb.utils.contains("TUNE_FEATURES", "cortexa76", "cortexa76:", "" ,d)}"
TUNECONFLICTS[aarch64] = "armv4 armv5 armv6 armv7 armv7a"

require conf/machine/include/arm/arch-armv8-2a.inc

# Little Endian base configs
AVAILTUNES += "cortexa76 cortexa76-crypto"
ARMPKGARCH_tune-cortexa76             = "cortexa76"
ARMPKGARCH_tune-cortexa76-crypto      = "cortexa76"
TUNE_FEATURES_tune-cortexa76          = "aarch64 cortexa76"
TUNE_FEATURES_tune-cortexa76-crypto   = "aarch64 cortexa76 crypto"
PACKAGE_EXTRA_ARCHS_tune-cortexa76             = "${PACKAGE_EXTRA_ARCHS_tune-armv8-2a} cortexa76"
PACKAGE_EXTRA_ARCHS_tune-cortexa76-crypto      = "${PACKAGE_EXTRA_ARCHS_tune-armv8-2a-crypto} cortexa76 cortexa76-crypto"
BASE_LIB_tune-cortexa76               = "lib64"
BASE_LIB_tune-cortexa76-crypto        = "lib64"
