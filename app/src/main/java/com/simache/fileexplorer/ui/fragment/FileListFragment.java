/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.simache.fileexplorer.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simache.fileexplorer.R;
import com.simache.fileexplorer.ui.adapter.FileListAdapter;
import com.simache.fileexplorer.ui.adapter.FileListAdapter.OnListItemClickListener;
import com.simache.fileexplorer.utils.FileTypesUtils;
import com.socks.library.KLog;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FileListFragment extends Fragment implements OnListItemClickListener{
    private final String TAG = "FileListFragment";
    private StringBuilder mPath = new StringBuilder("/sdcard/");
    private FileListAdapter mFileListAdapter;

    public static String[] sCheeseStrings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sCheeseStrings = getResources().getStringArray(R.array.list_items);
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.fragment_cheese_list, container, false);
        setupRecyclerView(rv);
        KLog.i("onCreateView");
        return rv;
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        /*recyclerView.setAdapter(new FileListAdapter(getActivity(),
                getRandomSublist(sCheeseStrings, 30)));*/
        mFileListAdapter = new FileListAdapter(getActivity(),
                FileTypesUtils.getFilesList("/sdcard/"));

        recyclerView.setAdapter(mFileListAdapter);
        mFileListAdapter.setOnListItemClickListener(this);
    }

    private List<String> getRandomSublist(String[] array, int amount) {
        ArrayList<String> list = new ArrayList<>(amount);
        Random random = new Random();
        while (list.size() < amount) {
            list.add(array[random.nextInt(array.length)]);
        }
        return list;
    }

    public List<String> getFilesList(String filePath) {
        List<String> filesList = new ArrayList<>();
        String[] list = null;
        File file = new File(filePath);
        if (file.isDirectory()) {
            list = file.list();
        }

        if (null != list) {
            for (String s : list) {
                filesList.add(s);
            }
            filesList = Arrays.asList(list);
        }

        return filesList;
    }

    @Override
    public void onClicked(String fileName) {
        KLog.e("file name = " + fileName);

        mPath.append(fileName);
        mPath.append("/");
        String childFilePath = mPath.toString();
        KLog.e("child file path : " + childFilePath);

        File file = new File(childFilePath);
        if (file.isDirectory()) {
            KLog.e("is directory");
            mFileListAdapter.setFileList(FileTypesUtils.getFilesList(childFilePath));
            mFileListAdapter.notifyDataSetChanged();
        }
        else {
            KLog.e("不是文件夹");
        }
    }


}
