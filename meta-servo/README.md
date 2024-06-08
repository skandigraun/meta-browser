Yocto/OpenEmbedded layer for Servo browser
==========================================

Maintainer: Gyorgy Sarvari/skandigraun@gmail.com

This layer depends on meta-clang, poky and meta-oe layers. At this time only Scarthgap is supported.

Important note: the current version of Servo must be compiled with Rust 1.78.

Unfortunately the required version of Rust is not provided poky. On a more positive note, it is available in meta-rust, however that layer does not support Scarthgap. For now, this layer ships Rust 1.78 - the recipes are based on the ones from poky Scarthgap.

- If you are using Rust for other recipes also in your image, then you have to use the shipped version for that too. (Make sure to set the appropriate `PREFERRED_VERSION`/`REQUIRED_VERSION` variables).
- Unfortunately I am not able to provide Rust support beyond what is required to compile Servo, mostly because I am not that familiar with the depths of the Rust compiler. I am however happy to get PRs to fix things.
- If you don't want to use this version of Rust for whatever reason, you can easily get rid of it by adding the folder to BBMASK variable in local.conf, e.g. `BBMASK += "meta-servo/recipe-devtools/rust"` - this will make Bitbake not to parse this folder at all. (Note that there is a bbappend in the same folder, which installs the rustc-dev component. That is required for successful compilation.)

This version of Servo layer has been tested only on one architecture so far, on aarch64 - PinePhonePro. It does work there. In theory it should compile to arm and x86_64 also, but I can test this only in the future.

In my understanding RISC-V is unsupported by Servo at this time, though this is also unverified for now.

Servo is a very quickly changing project, realistically it can't be tracked real time. I intend to create a new release every 7-10 days, with the latest commit from that point of time.

At this time only the browser itself is available in the layer (e.g. libs/crates for embedding are not added to SDK yet).

Feel free to open an issue or PR if you feel like it.

# Errors

```
Failed to create WR surfman: ContextCreationFailed(BadMatch) (thread main, at ports/servoshell/headed_window.rs:136)
Redirecting call to abort() to mozalloc_abort

Caught signal 11 in thread "main"
   0: <servoshell::backtrace::Print as core::fmt::Debug>::fmt
Segmentation fault (core dumped)
```

If Servo crashes right away upon starting with the above error, that seems to refer to some GPU driver error (hardware acceleration seems to require at least OpenGL 3.2).
 
It can be circumvented by starting Servo like `LIBGL_ALWAYS_SOFTWARE=1 ./servo`. Performance will likely suffer, though.

# Source of magic commits

This supposed to be just a reminder for me, where are the magic commits in the recipe coming from.

| Resource | Commit | Source of commit |
| -------- | ------ | ---------------- |
| stylo | 039959da0beac876128b86fa4b80908d8e55fbaf | This is the `HEAD` of `2024-05-15` branch (at the time of writing this), which is referenced by the top level Cargo.toml |
| media | 45756bef67037ade0f4f0125d579fdc3f3d457c8 | This is the `HEAD` of `main` branch (at this time), which is referenced by the top level Cargo.toml |
| webrender | 9d354adf8955b1390dd56db89e6d5a9ea7880391 | This is the `HEAD` of `0.64` branch currently, which is referenced by top level Cargo.toml |
| wgpu | d0a5e48aa7e84683114c3870051cc414ae92ac03 | Commit referenced by top level Cargo.toml |
| webxr | 88fd368d1cc110db0f6f5000de2e22a14c9423b5 | This is the `HEAD` of `main` branch, which is referenced by a number of Cargo.toml files, e.g ./components/canvas/Cargo.toml or ./components/script/Cargo.toml |
| fontsan | 8fbc406506cfd1f8ab60e625d1e926a0e72e1d8a | This is the `HEAD` of `main` branch, which is referenced by ./components/gfx/Cargo.toml |
| signpost | 7ed712507f343c38646b9d1fefd049166f9c9a18 | This is the `HEAD` of `master` branch, which is referenced by ./components/shared/profile/Cargo.toml |
| mozjs | df2365facf1b8cf3cd142ed384cef42bb474f6a1 | This is the `HEAD` of `main` branch, which is referenced by ./components/script/Cargo.toml |
