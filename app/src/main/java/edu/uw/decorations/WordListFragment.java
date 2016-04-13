package edu.uw.decorations;


import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple Fragment to display a list of words.
 */
public class WordListFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "WordList";

    private SimpleCursorAdapter adapter;

    public WordListFragment() {
        // Required empty public constructor
    }

    //A factory method to create a new fragment with some arguments
    public static WordListFragment newInstance() {
        WordListFragment fragment = new WordListFragment();
        Bundle bundle = new Bundle();
        //specify any arguments we want to give to the fragment. These can also come from the method params
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) { /*get values from arguments bundle*/ }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_word_list, container, false);

        //model
        String[] data = {"Dog","Cat","Android","Inconceivable"};

        //controller
        AdapterView listView = (AdapterView)rootView.findViewById(R.id.wordListView);

//        adapter = new ArrayAdapter<String>(
//                getActivity(), R.layout.list_item_layout, R.id.txtListItem, data);
        listView.setAdapter(adapter);

        getLoaderManager().initLoader(0, null, this);

        ContentResolver resolver = getActivity().getContentResolver();
        String[] projection = {UserDictionary.Words.WORD, UserDictionary.Words._ID};
        Cursor cursor = resolver.query(UserDictionary.Words.CONTENT_URI,
                projection, null, null, null);

        adapter = new SimpleCursorAdapter(
                getActivity(),
                R.layout.list_item_layout,
                cursor,
                new String[] {UserDictionary.Words.WORD},
                new int[] {R.id.txtListItem},
                0);

        //handle button input
        final TextView inputText = (TextView)rootView.findViewById(R.id.txtAddWord);
        Button addButton = (Button)rootView.findViewById(R.id.btnAddWord);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button click here
            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {UserDictionary.Words.WORD, UserDictionary.Words._ID};
        CursorLoader loader = new CursorLoader(
                getActivity(),
                UserDictionary.Words.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
