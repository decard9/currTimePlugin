package mytestplugin.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.texteditor.ITextEditor;
import org.omg.CORBA.Environment;
import org.eclipse.jface.text.ITextSelection;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);

		/*
		 * MessageDialog.openInformation( window.getShell(), "MyTestPlugin",
		 * "Hello, Eclipse world");
		 */

		/*
		 * IWorkbench wb = PlatformUI.getWorkbench(); IWorkbenchWindow win =
		 * wb.getActiveWorkbenchWindow();
		 */

		try {
			IWorkbenchPage page = window.getActivePage();
			IEditorPart editor = page.getActiveEditor();

			if (!(editor instanceof ITextEditor))
				return null;

			ITextEditor ite = (ITextEditor) editor;
			IDocument document = ite.getDocumentProvider().getDocument(ite.getEditorInput());
			ITextSelection selection = (ITextSelection) ite.getSelectionProvider().getSelection();
			int cursorPosition = selection.getOffset();
			
			long time = System.currentTimeMillis(); 
			SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
			String currStr = dayTime.format(new Date(time));
			currStr =currStr +System.getProperty("line.separator");


			// document.set("test1231293871832");//전체 텍스트를 일괄로 바꿈
			document.replace(cursorPosition, 0, currStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
