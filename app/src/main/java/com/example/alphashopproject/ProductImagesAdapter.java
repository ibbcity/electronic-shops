package com.example.alphashopproject;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.os.Environment.DIRECTORY_DOWNLOADS;

public class ProductImagesAdapter extends PagerAdapter {
    Dialog loadingDialog;
    private final List<String> productImages;
    private StorageReference storageReference;
    private StorageReference Reference;
    Context context;
  private int coounter=1;
    // int pos;
    String  fileName_3image;
    String _3_Texture;
    String _3_Texture_image;

    //List<HorizontalProductScrollModel> ProductlList = new ArrayList<>();
    public ProductImagesAdapter(Context context , List<String> productImages,String  fileName_3image,String _3_Texture,String _3_Texture_image) {
        this.context=context;
        this.productImages = productImages;
        this.fileName_3image=fileName_3image;
        this._3_Texture=_3_Texture;
        this._3_Texture_image=_3_Texture_image;
        // this.pos=pos;
    }
    @Override
    public int getCount() {
        return productImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        final ImageView productImage=new ImageView(container.getContext());
        Glide.with(container.getContext()).load(productImages.get(position)).apply(new RequestOptions().placeholder(R.mipmap.pic)).into(productImage);
        container.addView(productImage,0);
        try {
            productImage.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    //
                    //download();
                    loadingDialog = new Dialog(context);
                    loadingDialog.setContentView(R.layout.loading_progress_dialog);
                    loadingDialog.setCancelable(false);
                    loadingDialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.slider_background));
                    loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    loadingDialog.show();
                   // Toast.makeText(context,fileName_3image,Toast.LENGTH_SHORT).show();
                    downloadFile(_3_Texture);
                    downloadFile(_3_Texture_image);
                    downloadFile(fileName_3image);

                    // downloadFile("a.obj");

                }

            });
        }catch (Exception e){
            Toast.makeText(productImage.getContext(),""+e,Toast.LENGTH_SHORT).show();;
        }

        return productImage;
    }

    private void download() {
        storageReference=FirebaseStorage.getInstance().getReference();
        Reference=storageReference.child(fileName_3image);
        Reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               coounter++;
               if(coounter==3) {
                   String url = uri.toString();
                   downloadFiles(context, fileName_3image, Environment.getExternalStorageDirectory() + "/", url);
                   // Toast.makeText(context, "Ok Ok Ok", Toast.LENGTH_SHORT).show();
                   Log.d("tagResult", "3d image added successfull");
                   Toast.makeText(context, "downloaded", Toast.LENGTH_SHORT).show();
                   ///////////////////
                /*
                Intent show = new Intent();
                show.setAction("transBundle.apppp");
                context.startActivity(show);

                 */
               }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(context, "fail"+e.getMessage(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void downloadFiles(Context context, String fileName_3image_arg, String desinationDirectory, String url) {

        DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri=Uri.parse(url);
        DownloadManager.Request request=new DownloadManager.Request(uri);

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, desinationDirectory, fileName_3image_arg );
        downloadManager.enqueue(request);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    private void downloadFile(final String file_name) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://alphashopproject.appspot.com");
        StorageReference  islandRef = storageRef.child(file_name);

        File rootPath = new File(Environment.getExternalStorageDirectory(), "file_name");
        if(!rootPath.exists()) {

            Toast.makeText(context, ""+rootPath.mkdirs(), Toast.LENGTH_SHORT).show();
        }

        final File localFile = new File(rootPath,file_name);

        islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.e("firebase ", ";local tem file created  created " + localFile.toString());
                Toast.makeText(context, "doenload complated", Toast.LENGTH_SHORT).show();
                coounter++;
                if (coounter == 3) {
                    loadingDialog.dismiss();
                    ////////////////
                    Intent show = new Intent();
                    show.setAction("3dmodel");
                    show.putExtra("file_name", file_name);
                    //show.putExtra("_3_Texture",_3_Texture);
                    //  show.putExtra("_3_Texture_image",_3_Texture_image);
                    context.startActivity(show);
                    //  updateDb(timestamp,localFile.toString(),position);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("firebase ",";local tem file not created  created " +exception.toString());
                Toast.makeText(context, "doenload fail"+exception, Toast.LENGTH_SHORT).show();
            }
        });
    }


}
