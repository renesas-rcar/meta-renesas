python __anonymous () {
    deps = d.getVar('GTKBASE_RRECOMMENDS', True).split()
    pn = d.getVar('PN', True)
    if pn.startswith('lib32-'):
        deps.remove('liberation-fonts')
        d.setVar('GTKBASE_RRECOMMENDS', ' '.join(deps))
}
