python __anonymous () {
    pn = d.getVar('PN', True)
    if pn.startswith('lib32-'):
        deps = d.getVar('RDEPENDS_' + pn + '-ptest', True).split()
        deps.remove('liberation-fonts')
        d.setVar('RDEPENDS_' + pn + '-ptest', ' '.join(deps))
}
