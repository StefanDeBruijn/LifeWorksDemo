package lifeworks.com.demo.business.listener;

/**
 * Listens to an OnClick Event within a RecyclerView
 *
 * @param <T>
 */
public interface OnRecyclerViewInteractionListener<T> {

    /**
     * Called by a RecyclerView when an item is clicked
     *
     * @param object         Object tied to clicked position
     * @param objectPosition clicked position
     */
    void onSelectItem(T object, int objectPosition);

}
