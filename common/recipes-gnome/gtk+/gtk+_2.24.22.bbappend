PACKAGESPLITFUNCS_prepend += " add_private_libs_packages "

python add_private_libs_packages () {
    import os
    import re

    gtk_libdir = d.expand('${libdir}/gtk-2.0/${LIBV}')
    immodules_root = os.path.join(gtk_libdir, 'immodules')
    printmodules_root = os.path.join(gtk_libdir, 'printbackends');

    dvar = d.getVar('PKGD', True)

    for dirpath, dirnames, filenames in os.walk(dvar + immodules_root):
        for f in filenames:
            m = re.match('^im-(.*)\.so$', f)
            if m:
                immodule = m.group(1)
                d.setVar('PRIVATE_LIBS_' + 'gtk-immodule-' + immodule , 'im-' + immodule + '.so')

    for dirpath, dirnames, filenames in os.walk(dvar + printmodules_root):
        for f in filenames:
            m = re.match('^libprintbackend-(.*)\.so$', f)
            if m:
                printbackend = m.group(1)
                d.setVar('PRIVATE_LIBS_' + 'gtk-printbackend-' + printbackend , 'libprintbackend-' + printbackend + '.so')
}