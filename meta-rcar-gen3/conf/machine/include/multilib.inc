# Configuration for Multilib
require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 = "armv7ahf-neon"

# Configuration for SDK
SDKTARGETSYSROOT_virtclass-multilib-lib32 = \
    "${SDKPATH}/sysroots/aarch64-pokymllib32-linux"

# Add the preferred version for gdb-cross canadian
# and binutils-cross canadian.
PREFERRED_VERSION_binutils-cross-canadian-arm = "${BINUVERSION}"
PREFERRED_VERSION_gdb-cross-canadian-arm = "${GDBVERSION}"
