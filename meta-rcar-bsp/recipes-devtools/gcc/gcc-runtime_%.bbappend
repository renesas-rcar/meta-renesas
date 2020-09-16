# cortex-a76 is ARMv8.2-a based but libatomic explicitly asks for -march=armv8.1-a
# which caused -march conflicts in gcc
TUNE_CCARGS_remove_rcar-gen3 = "-mcpu=cortex-a76 -mcpu=cortex-a76+crypto"
