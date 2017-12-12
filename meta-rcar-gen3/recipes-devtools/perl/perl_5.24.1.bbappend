# Fix the issue missed Perl modules in navtive SDK sysroot
FILES_${PN}_append_class-nativesdk = " ${libdir}/perl/${PV}/*"
