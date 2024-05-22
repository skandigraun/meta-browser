
do_install:append(){
    # servo build-script requirement
    rust_runx install rustfmt

    # install rustc-dev component (to build crown), based on
    # https://users.rust-lang.org/t/rustup-add-component-for-custom-toolchain/81846

    rust_runx dist

    INSTALL_PREFIX=`grep prefix ${S}/config.toml | cut -f2 -d\"`

    rm -rf ${S}/build/dist/rustc-dev-temp
    mkdir ${S}/build/dist/rustc-dev-temp

    cp ${S}/build/dist/rustc-dev*tar.gz ${S}/build/dist/rustc-dev-temp/
    cd ${S}/build/dist/rustc-dev-temp
    tar xfz rustc-dev*tar.gz
    rm rustc-dev*tar.gz
    cd rustc-dev*
    ./install.sh --prefix=${INSTALL_PREFIX} --bindir=${INSTALL_PREFIX}/bin \
                 --libdir=${INSTALL_PREFIX}/lib --datadir=${INSTALL_PREFIX}/share \
                 --mandir=${INSTALL_PREFIX}/share/man --components=rustc-dev
}
