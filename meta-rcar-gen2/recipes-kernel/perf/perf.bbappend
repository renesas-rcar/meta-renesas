do_configure_prepend () {
    if [ -e "${S}/tools/lib/subcmd/Makefile" ]; then
        sed -i 's,CC = $(CROSS_COMPILE)gcc,#CC,' ${S}/tools/lib/subcmd/Makefile
        sed -i 's,AR = $(CROSS_COMPILE)ar,#AR,' ${S}/tools/lib/subcmd/Makefile
    fi
}

RDEPENDS_${PN}-python =+ "python-modules"
