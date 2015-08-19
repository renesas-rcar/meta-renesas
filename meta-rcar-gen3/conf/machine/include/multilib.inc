# Configuration for Multilib
require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 = "armv7ahf"

# Configuration for SDK
SDKTARGETSYSROOT_virtclass-multilib-lib32 = \
    "${SDKPATH}/sysroots/aarch64-pokymllib32-linux"
