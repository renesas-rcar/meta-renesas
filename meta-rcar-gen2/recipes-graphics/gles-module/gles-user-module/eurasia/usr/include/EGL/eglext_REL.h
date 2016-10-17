/*
* Copyright(c) 2011-2013 Renesas Electronics Corporation
* RENESAS ELECTRONICS CONFIDENTIAL AND PROPRIETARY.
* This program must be used solely for the purpose for which
* it was furnished by Renesas Electronics Corporation. No part of this
* program may be reproduced or disclosed to others, in any
* form, without the prior written permission of Renesas Electronics
* Corporation.
*/

#ifndef __eglext_REL_h__
#define __eglext_REL_h__

#ifdef __cplusplus
extern "C" {
#endif

#include <EGL/eglplatform.h>

/* EGL_EXTENSION_REL_QUERY_SPM_EVENT */
#ifndef EGL_REL_query_spm_event
#define EGL_REL_query_spm_event 1
typedef struct EGLParamBufferInfoREL_TAG
{
	EGLBoolean spmevent;
	EGLint	used_size;
	EGLint	free_size;
} EGLParamBufferInfoREL;
#ifdef EGL_EGLEXT_PROTOTYPES
EGLAPI EGLBoolean eglQuerySPMEventREL(EGLParamBufferInfoREL *parambufferinfo);
#endif /* EGL_EGLEXT_PROTOTYPES */
typedef EGLBoolean (EGLAPIENTRYP PFNEGLQUERYSPMEVENTRELPROC)(EGLParamBufferInfoREL *parambufferinfo);
#endif

/* EGL_EXTENSION_REL_CBUF_SIZE */
#ifndef EGL_REL_cbuf_size
#define EGL_REL_cbuf_size 1
#define  EGL_CONTEXT_GLES2_VERTEX_BUFFER_REL          0x3301
#define  EGL_CONTEXT_GLES2_INDEX_BUFFER_REL           0x3302
#define  EGL_CONTEXT_GLES2_VDM_BUFFER_REL             0x3303
#define  EGL_CONTEXT_GLES2_PDSVERT_BUFFER_REL         0x3304
#define  EGL_CONTEXT_GLES2_PREGEN_MTECOPY_BUFFER_REL  0x3305

#define  EGL_CONTEXT_GLES1_VERTEX_BUFFER_REL          0x3301
#define  EGL_CONTEXT_GLES1_INDEX_BUFFER_REL           0x3302
#define  EGL_CONTEXT_GLES1_VDM_BUFFER_REL             0x3303
#define  EGL_CONTEXT_GLES1_PDSVERT_BUFFER_REL         0x3304
#define  EGL_CONTEXT_GLES1_PREGEN_MTECOPY_BUFFER_REL  0x3305
#define  EGL_CONTEXT_GLES1_PREGEN_PDSVERT_BUFFER_REL  0x3307

#define  EGL_CONTEXT_GLES_VERTEX_BUFFER_REL           0x3301
#define  EGL_CONTEXT_GLES_INDEX_BUFFER_REL            0x3302
#define  EGL_CONTEXT_GLES_VDM_BUFFER_REL              0x3303
#define  EGL_CONTEXT_GLES_PDSVERT_BUFFER_REL          0x3304
#define  EGL_CONTEXT_GLES_PREGEN_MTECOPY_BUFFER_REL   0x3305
#define  EGL_CONTEXT_GLES_PREGEN_PDSVERT_BUFFER_REL   0x3307

#ifdef EGL_EGLEXT_PROTOTYPES
EGLAPI EGLContext eglCreateContextREL(EGLDisplay dpy, EGLConfig config,EGLContext share_context,const EGLint *attrib_list);
EGLAPI EGLBoolean eglQueryContextREL(EGLDisplay dpy, EGLContext ctx,EGLint attribute, EGLint *value);
#endif /* EGL_EGLEXT_PROTOTYPES */
typedef EGLContext (EGLAPIENTRYP PFNEGLCREATECONTEXTRELPROC)(EGLDisplay dpy, EGLConfig config,EGLContext share_context,const EGLint *attrib_list);
typedef EGLBoolean (EGLAPIENTRYP PFNEGLQUERYCONTEXTRELPROC)(EGLDisplay dpy, EGLContext ctx,EGLint attribute, EGLint *value);
#endif

/* EGL_EXTENSION_REL_DEBUG_LOG */
#ifndef EGL_REL_debug_log
#define EGL_REL_debug_log 1
#ifdef EGL_EGLEXT_PROTOTYPES
EGLAPI void eglDebugLogOutputREL (void);
EGLAPI void EGLAPIENTRY eglDebugLogMemLoggingStartREL (void);
EGLAPI void EGLAPIENTRY eglDebugLogMemLoggingStopREL (void);
#endif /* EGL_EGLEXT_PROTOTYPES */
typedef void (EGLAPIENTRYP PFNEGLDEBUGLOGOUTPUTRELPROC) (void);
typedef void (EGLAPIENTRYP PFNEGLDEBUGLOGMEMLOGGINGSTARTRELPROC) (void);
typedef void (EGLAPIENTRYP PFNEGLDEBUGLOGMEMLOGGINGSTOPRELPROC) (void);
#endif

/* EGL_EXTENSION_REL_GET_MEMINFO */
#ifndef EGL_REL_get_meminfo
#define EGL_REL_get_meminfo 1
#ifdef EGL_EGLEXT_PROTOTYPES
EGLAPI void EGLAPIENTRY eglGetMemInfoREL(EGLint *host_current, EGLint *host_peak, EGLint *device_current, EGLint *device_peak);
#endif /* EGL_EGLEXT_PROTOTYPES */
typedef void (EGLAPIENTRYP PFNEGLGETMEMINFORELPROC) (EGLint *host_current, EGLint *host_peak, EGLint *device_current, EGLint *device_peak);
#endif

/* EGL_EXTENSION_REL_QUERY_PDSFRAGBUFFER_INFO */
#ifndef EGL_REL_query_pdsfragbuffer_info
#define EGL_REL_query_pdsfragbuffer_info 1
typedef struct EGLPDSFragBufferInfoREL_TAG
{
	EGLBoolean	overflow;
	EGLint		used_size;
	EGLint		free_size;
} EGLPDSFragBufferInfoREL;
#ifdef EGL_EGLEXT_PROTOTYPES
EGLAPI EGLBoolean eglQueryPDSFragBufferInfoREL(EGLPDSFragBufferInfoREL *pdsfragbufferinfo);
#endif /* EGL_EGLEXT_PROTOTYPES */
typedef EGLBoolean (EGLAPIENTRYP PFNEGLQUERYPDSFRAGBUFFERINFORELPROC) (EGLPDSFragBufferInfoREL *pdsfragbufferinfo);
#endif /* EGL_REL_query_pdsfragbuffer_info */

typedef struct EGLNativePixmapTypeREL_TAG
{
	EGLint	width;
	EGLint	height;
	EGLint	stride;
	EGLint	usage;
	EGLint	format;
	void*	pixelData;
} EGLNativePixmapTypeREL;

#define EGL_NATIVE_PIXFORMAT_RGB565_REL			0
#define EGL_NATIVE_PIXFORMAT_ARGB1555_REL		1
#define EGL_NATIVE_PIXFORMAT_ARGB8888_REL		2
#define EGL_NATIVE_PIXFORMAT_ARGB4444_REL		3
#define EGL_NATIVE_PIXFORMAT_UYVY_REL			4	// YUV422 packed
#define EGL_NATIVE_PIXFORMAT_NV12_REL			5	// YUV420 planer 2 plane
#define EGL_YUV_COLORSPACE_BT601_CONFORMANT_RANGE_REL	0x100
#define EGL_YUV_COLORSPACE_BT601_FULL_RANGE_REL		0x200
#define EGL_YUV_COLORSPACE_BT709_CONFORMANT_RANGE_REL	0x300
#define EGL_YUV_COLORSPACE_BT709_FULL_RANGE_REL		0x400

#ifdef __cplusplus
}
#endif

#endif
