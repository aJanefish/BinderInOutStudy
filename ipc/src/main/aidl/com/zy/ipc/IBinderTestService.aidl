// IBinderTestService.aidl
package com.zy.ipc;

import com.zy.ipc.AidlBean;

interface IBinderTestService {
   void InTest(in AidlBean bean);
   void InTestShow();
   void OutTest(out AidlBean bean);
   void OutTestShow();
   void InOutTest(inout AidlBean bean);
   void InOutTestShow();
}
