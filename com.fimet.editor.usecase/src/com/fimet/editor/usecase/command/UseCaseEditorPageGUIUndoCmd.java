package com.fimet.editor.usecase.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.text.undo.IDocumentUndoManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import com.fimet.editor.usecase.Activator;
import com.fimet.editor.usecase.UseCaseEditor;
/**
 * 
 * @author Marco A. Salazar
 * @email marcoasb99@ciencias.unam.mx
 *
 */
public class UseCaseEditorPageGUIUndoCmd extends AbstractHandler {
    public Object execute(ExecutionEvent event) throws ExecutionException {
    	try {
    		IEditorPart editorPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			if (editorPart instanceof UseCaseEditor) {
				UseCaseEditor editor = (UseCaseEditor)editorPart;
				if (editor.getModifier().areDirtyPagesGui()) {
					if (!editor.getSourcePage().isDirty()) {
						editor.undoGui();
					} else {
						IDocumentUndoManager undoManager = editor.getSourcePage().getDocumentUndoManager();
						if (undoManager != null)
							undoManager.undo();
					}
				} else {
					IDocumentUndoManager undoManager = editor.getSourcePage().getDocumentUndoManager();
					if (undoManager != null)
						undoManager.undo();
				}
			}

    	} catch (Exception e) {
    		Activator.getInstance().error("Undo page editor", e);
    	}
        return null;
    }

}