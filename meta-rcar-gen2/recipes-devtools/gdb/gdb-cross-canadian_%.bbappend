# Fix the issue of RDEPENDS of libncurses.so.5
# libncurses.so.5: undefined symbol: _nc_putchar
RDEPENDS_${PN} += "nativesdk-ncurses-libncurses"
