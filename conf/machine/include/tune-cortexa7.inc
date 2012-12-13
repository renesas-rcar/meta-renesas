DEFAULTTUNE ?= "cortexa7-neon"

require conf/machine/include/arm/arch-armv7a.inc

TUNEVALID[cortexa7] = "Enable Cortex-A7 specific processor optimizations"
TUNE_CCARGS += "${@bb.utils.contains("TUNE_FEATURES", "cortexa7", "-mtune=cortex-a7", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa7 cortexa7t cortexa7-neon"
TUNE_FEATURES_tune-cortexa7 = "${TUNE_FEATURES_tune-armv7a} cortexa7"
TUNE_FEATURES_tune-cortexa7t = "${TUNE_FEATURES_tune-armv7at} cortexa7"
TUNE_FEATURES_tune-cortexa7-neon = "${TUNE_FEATURES_tune-armv7a-neon} cortexa7"
PACKAGE_EXTRA_ARCHS_tune-cortexa7 = "${PACKAGE_EXTRA_ARCHS_tune-armv7a}"
PACKAGE_EXTRA_ARCHS_tune-cortexa7t = "${PACKAGE_EXTRA_ARCHS_tune-armv7at}"
PACKAGE_EXTRA_ARCHS_tune-cortexa7-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7a-neon}"

# VFP Tunes
AVAILTUNES += "cortexa7hf cortexa7thf cortexa7hf-neon"
TUNE_FEATURES_tune-cortexa7hf ?= "${TUNE_FEATURES_tune-armv7ahf} cortexa7"
TUNE_FEATURES_tune-cortexa7thf ?= "${TUNE_FEATURES_tune-armv7athf} cortexa7"
TUNE_FEATURES_tune-cortexa7hf-neon ?= "${TUNE_FEATURES_tune-armv7ahf-neon} cortexa7"
PACKAGE_EXTRA_ARCHS_tune-cortexa7hf = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf}"
PACKAGE_EXTRA_ARCHS_tune-cortexa7thf = "${PACKAGE_EXTRA_ARCHS_tune-armv7athf}"
PACKAGE_EXTRA_ARCHS_tune-cortexa7hf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf-neon}"
