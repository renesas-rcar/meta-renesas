# cortex-a76 is ARMv8.2-a based but libatomic explicitly asks for -march=armv8.1-a
# which caused -march conflicts in gcc
TUNE_CCARGS:remove:r8a779a0 = "-mcpu=cortex-a76 -mcpu=cortex-a76+crypto"
TUNE_CCARGS:remove:r8a779g0 = "-mcpu=cortex-a76 -mcpu=cortex-a76+crypto"
TUNE_CCARGS:remove:r8a779h0 = "-mcpu=cortex-a76 -mcpu=cortex-a76+crypto"
