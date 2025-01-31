package somethingrandom.app;

import somethingrandom.entity.CommonItemFactory;
import somethingrandom.interfaceadapters.ViewManagerModel;
import somethingrandom.interfaceadapters.additem.AddItemController;
import somethingrandom.interfaceadapters.additem.AddItemPresenter;
import somethingrandom.interfaceadapters.additem.AddItemViewModel;
import somethingrandom.usecase.AddItemDataAccessInterface;
import somethingrandom.usecase.AddItemInputBoundary;
import somethingrandom.usecase.AddItemInteractor;
import somethingrandom.usecase.AddItemOutputBoundary;
import somethingrandom.view.AddItemView;

public class AddItemUseCaseFactory {

    /** Prevent instantiation. */
    private AddItemUseCaseFactory() {}

    public static AddItemView create(
        ViewManagerModel viewManagerModel, AddItemViewModel addItemViewModel, AddItemDataAccessInterface userDataAccessObject) {

        AddItemController addItemController = createAddItemUseCase(viewManagerModel, addItemViewModel, userDataAccessObject);
        return new AddItemView(addItemController, addItemViewModel);
    }

    private static AddItemController createAddItemUseCase(ViewManagerModel viewManagerModel, AddItemViewModel addItemViewModel, AddItemDataAccessInterface addItemDataAccessObject) {
        AddItemOutputBoundary addItemOutputBoundary = new AddItemPresenter(addItemViewModel,viewManagerModel);
        CommonItemFactory commonItemFactory = new CommonItemFactory();

        AddItemInputBoundary addItemInteractor = new AddItemInteractor(addItemDataAccessObject, addItemOutputBoundary, commonItemFactory);

        return new AddItemController(addItemInteractor);

    }
}
