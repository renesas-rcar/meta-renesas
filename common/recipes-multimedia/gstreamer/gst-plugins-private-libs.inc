PACKAGESPLITFUNCS_prepend += " add_private_libs_packages "

python add_private_libs_packages () {
    import os
    import re

    pn = d.getVar('PN', True)
    gst_libdir = d.expand('${libdir}/gstreamer-${LIBV}')

    dvar = d.getVar('PKGD', True)

    for dirpath, dirnames, filenames in os.walk(dvar + gst_libdir):
        for f in filenames:
            m = re.match('libgst(.*)\.so$', f)
            if m:
                plugins = m.group(1)
                d.setVar('PRIVATE_LIBS_' + pn + '-' + plugins , 'libgst' + plugins + '.so')
}
